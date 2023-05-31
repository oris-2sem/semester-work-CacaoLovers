import {makeAutoObservable} from 'mobx'
import AuthService from "../services/AuthService";
import jwt from "jwt-decode"

export default class Account {
    user = {}
    isAuth = false

    constructor() {
        makeAutoObservable(this)
    }

    setAuth(bool){
        this.isAuth = bool;
    }
    
    setUser(user){
        this.user = user
    }

    async login(username, password){
        try {
            let response = await AuthService.refreshLogin();
            if (response.status === 401 && username && password) response = await AuthService.login(username, password)
            if (!response){
                return {error: "Нет связи с сервером"}
            }
            if (response.status === 401) {
                return {error: "Неверный логин или пароль"}
            }

            const tokens = await response.json()
            const decodedAccessToken = jwt(tokens.accessToken)
            const uuid = decodedAccessToken.uuid

            localStorage.setItem("token", tokens.accessToken)
            if (uuid && uuid !== localStorage.getItem("uuid")) localStorage.setItem("uuid", uuid)
            this.setAuth(true)
            this.setUser({username: decodedAccessToken.sub, role: decodedAccessToken.role, id: localStorage.getItem("uuid")})
            return this.user
        } catch (e){
            console.log(e)
        }
    }

    async getAccount(uuid){
        let result = await AuthService.getAccount(uuid)
        if (result.status === 401) return "Ошибка авторизации"
        return result
    }

    async registration({username, password, email}){
        try {
            return await AuthService.signUp({username, password, email});
        } catch (e){
            console.log(e)
        }
    }

    async logout(){
        try {
            const response = await AuthService.logout();
            localStorage.removeItem("token")
            localStorage.removeItem("uuid")
            this.setAuth(false)
            this.setUser({})
        } catch (e){
            console.log(e.response?.data?.message)
        }
    }
}