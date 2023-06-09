import React, {useEffect, useRef, useState} from "react";
import styles from "./profile.edit.module.css"
import {useNavigate, useParams} from "react-router-dom";


export const ProfileEditModal = ({account, closeModal = f => f}) => {

    let firstName = useRef(null)
    let lastName = useRef(null)
    let email = useRef(null)
    let city = useRef(null)
    let [cities, setCities] = useState([]);
    let navigate = useNavigate()
    let param = useParams()

    useEffect(() => {
        async function getCities() {
            let response = await fetch("http://localhost:8080/city", {
                headers: {
                    "Authorization": localStorage.getItem("token"),
                }
            })
            response = await response.json()
            setCities(response)
        }
        getCities()
    }, [])

    async function updateAccount() {
        account.firstName = firstName.current.value;
        account.lastName = lastName.current.value;
        account.email = email.current.value;
        account.city = {"name": city.current.value};
        await fetch("http://localhost:8080/account", {
            headers: {
                "Authorization": localStorage.getItem("token"),
                "Content-Type": "application/json"
            },
            method: "PUT",
            body: JSON.stringify(account)
        })
        closeModal()
        navigate(`/profile/${param.userId}/info`)
    }


    return (
        <form className={styles.container}>
            <div  className={styles.container__name_field}>Имя</div>
            <input type="text" defaultValue={account?.firstName} ref={firstName}/>
            <div className={styles.container__name_field}>Фамилия</div>
            <input type="text" defaultValue={account?.lastName} ref={lastName}/>
            <div className={styles.container__name_field}>Почта</div>
            <input type="email" defaultValue={account?.email} ref={email}/>
            <div className={styles.container__name_field}>Город</div>
            <select defaultValue={account?.city} ref={city}>
                {cities.map((city) => {
                    return (<option value={city?.name}>{city?.name}</option>)
                })}
            </select>
            <button type="button" onClick={() => updateAccount()}>Сохранить</button>
        </form>)
}