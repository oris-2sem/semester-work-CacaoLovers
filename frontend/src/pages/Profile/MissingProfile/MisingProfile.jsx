import {MissingEntity} from "../../../components/MissingEntity/MissingEntity";
import React, {useState} from "react";
import {catSearchOne, catSearchThree, catSearchTwo} from "../../../image";
import {useEffect} from "react";
import {useParams} from "react-router-dom";



export const MissingProfile = () => {

    const param = useParams();
    const [missings, setMissings] = useState([])

    useEffect(() => {
        async function getMissings() {
            let response = await fetch(`http://localhost:8080/missing/owner/${param.userId}`, {
                headers: {
                    "Authorization": localStorage.getItem("token")
                },
                method: "GET"
            })
            response = await response.json()
            setMissings(response)
        }
        getMissings()
    }, [])

    return (
        <>
            {missings.map((missing) => {
                return (<MissingEntity missing={missing}/>)
            })}
        </>
    )
}