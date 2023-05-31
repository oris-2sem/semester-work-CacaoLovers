import {createContext, useContext, useEffect, useState} from "react";
import Account from "./account";
import MissingService from "../services/MissingService";

const store = new Account()

const MissingContext = createContext({});

export const useMissing = () => useContext(MissingContext)

export const MissingProvider = ({children}) => {

    const [missing, setMissing] = useState({
        posX: 0,
        posY: 0,
        description: "",
        name: "",
        kind: "",
        gender: "Неопределен",
        district: {
            name: "",
            city: ""
        },
        owner: {
            id: localStorage.getItem("uuid")
        },
        type: "FOUND",
        status: "ACTIVE",
        image: {
            id: "",
            path: ""
        }
    })



    let addMissing = async () => {
        await MissingService.addMissing(missing)
    }

    let getMissing = async () => {
        return await MissingService.getMissing()
    }

    let deleteMissing = async (uuid) => {
        return await MissingService.deleteMissing(uuid)
    }

    return (
        <MissingContext.Provider value={{missing, addMissing, getMissing, deleteMissing}}>
            {children}
        </MissingContext.Provider>
    )

}