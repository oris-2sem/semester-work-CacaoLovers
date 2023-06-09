import {createContext, useContext, useEffect, useState} from "react";
import Account from "./account";
import MissingService from "../services/MissingService";
import {useNavigate} from "react-router-dom";

const MissingContext = createContext({});

export const useMissing = () => useContext(MissingContext)

export const MissingProvider = ({children}) => {

    let navigate = useNavigate();

    const [missing, setMissing] = useState({
        posX: 0,
        posY: 0,
        description: "",
        name: "",
        kind: "",
        gender: "Неопределен",
        address: "",
        district: {
            name: "",
            city: ""
        },
        owner: {
            id: localStorage.getItem("uuid")
        },
        type: "",
        status: "ACTIVE",
        image: {
            id: "",
            path: "search.png"
        }
    })



    let addMissing = async () => {
        await MissingService.addMissing(missing)
        navigate(`/missing/${missing.type}`)
    }

    let getMissing = async (type) => {
        return await MissingService.getMissing(type)
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