import $api from "../http";

const BASE_URI = "localhost"

export default class AuthService {
    static async login(username, password){
        return await fetch(`http://${BASE_URI}:8080/auth/token`, {
            headers: {
                "Content-type": "application/x-www-form-urlencoded"
            },
            body: `username=${username}&password=${password}`,
            method: "POST",
            credentials: "include"
        }).then();
    }

    static async refreshLogin(){
        return fetch(`http://${BASE_URI}:8080/auth/token`, {
            method: "POST",
            credentials: "include",
        }).then();
    }

    static async signUp({username, email, password}) {
        return await fetch(`http://${BASE_URI}:8080/account/sign`,{
            method: "POST",
            headers: {
              "Content-type": "application/json"
            },
            body: JSON.stringify({username, email, password, city: {name: "Казань"}, status: "CONFIRMED", role: "USER"})
        })
    }

    static async getAccount(uuid){
        return fetch(`http://${BASE_URI}:8080/account/${uuid}`, {
            headers: {
                "Authorization": localStorage.getItem("token")
            }
        });
    }
    static async logout() {
        return await fetch(`http://${BASE_URI}:8080/logout`, {
            method: "POST",
            credentials: "include"
        }).then();
    }
}

