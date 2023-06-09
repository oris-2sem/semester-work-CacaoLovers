import styles from "./authorization.module.css"
import React, {useEffect, useState} from "react";
import {AuthorizationForm} from "./AuthorizationForm";
import {RegistrationForm} from "./RegistrationForm";
import cn from 'classnames'

export const Authorization = ({closeModal = (f) => f, setTitle = (f) => f}) => {

    const [state, setState] = useState("auth")

    useEffect(() => {
        setTitle(<AuthorizationHeader currentAuth={state} setAuth={setState}/>)
    }, [state])

    return (
        <div className={styles.auth_container}>
            {state === "auth" ? (<AuthorizationForm closeModal={closeModal}/>) : <RegistrationForm closeModal={closeModal}/>}
        </div>
    );
}

const AuthorizationHeader = ({currentAuth, setAuth = (f) => f}) => {
    return (
        <div className={styles.auth_container__header}>
            <div onClick={() => setAuth("auth")} className={cn(styles.auth_container__header__button, currentAuth === "auth" && styles.auth_container__header__button__isActive)}>Авторизация</div>
            <div onClick={() => setAuth("reg")} className={cn(styles.auth_container__header__button, currentAuth === "reg" && styles.auth_container__header__button__isActive)}>Регистрация</div>
        </div>
    );
}

//className={(currentAuth) => cn(styles.auth_container__header__button, currentAuth !== "auth" && styles.auth_container__header__button__isActive)}