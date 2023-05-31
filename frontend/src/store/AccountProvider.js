import {createContext, useContext, useEffect, useState} from "react";
import Account from "./account";

const store = new Account()

const AccountContext = createContext({});

export const useAccount = () => useContext(AccountContext)

export const AccountProvider = ({children}) => {
    const [auth, setAuth] = useState(false)
    const [account, setAccount] = useState({username: "", role: ""})

    let login = async (username, password) => {
        let result = await store.login(username, password)

        if (!result.error) {
            setAccount(result)
            setAuth(true)
        }
        return result
    }

    let register = async ({username, password, email}) => {
        let result = await store.registration({username, password, email})
        await login(username, password)
        return result
    }

    let logout = async () => {
        await store.logout()
        setAccount({})
        setAuth(false)
    }

    let getAccount = async (uuid) => {
        return store.getAccount(uuid)
    }

    useEffect(() => {
        if (localStorage.getItem("token")) login()
    }, [])


    return (
        <AccountContext.Provider value={{auth, account, login, logout, register, getAccount}}>
            {children}
        </AccountContext.Provider>
    )

}