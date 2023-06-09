import {createContext, useContext, useEffect, useState} from "react";
import Account from "./account";
import {useNavigate} from "react-router-dom";

const store = new Account()

const AccountContext = createContext({});

export const useAccount = () => useContext(AccountContext)

export const AccountProvider = ({children}) => {
    const [auth, setAuth] = useState(false)
    const [account, setAccount] = useState({username: "", role: ""})
    const [accountCash, setAccountCash] = useState(false)
    const navigate = useNavigate()



    let login = async (username, password) => {
        let result = await store.login(username, password)

        if (!result.error) {
            setAccount(result)
            setAccountCash(result)
            setAuth(true)
        }
        return result
    }

    let register = async ({username, password, email}) => {
        await store.registration({username, password, email})
        let result = await store.login(username, password)
        setAccountCash(!accountCash)
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

    let exit = async () => {
        await logout()
        navigate("/")
    }

    useEffect(() => {
        if (localStorage.getItem("token")) login()
    }, [])


    return (
        <AccountContext.Provider value={{auth, account, accountCash, login, logout, register, getAccount, exit}}>
            {children}
        </AccountContext.Provider>
    )

}