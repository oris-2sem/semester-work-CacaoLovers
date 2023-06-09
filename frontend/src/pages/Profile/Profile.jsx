import React from 'react'
import styles from './profile.module.css'
import {Helmet} from "react-helmet";
import {Link, NavLink, Outlet, useParams} from "react-router-dom";
import cn from "classnames"
import {useAccount} from "../../store/AccountProvider";
import {useEffect, useState} from "react";
import {Modal} from "../../components/Modal/Modal";
import {ProfileEditModal} from "./ProfileEditModal/ProfileEditModal";




export const Profile = () => {

    const {account, getAccount } = useAccount()
    const params = useParams()
    const [user, setUser] = useState()
    const [visible, setVisible] = useState(false)

    useEffect(() => {
        async function getProfile() {
            let response = await getAccount(params.userId)
            response = await response.json()
            setUser(response)
        }
        getProfile()
    }, [])

    return (
        <>
            <Modal title={"Редактирование пользователя"} content={<ProfileEditModal account={user} closeModal={() => setVisible(false)}/>} visible={visible} onClose={() => setVisible(false)}/>
            <Helmet>
                <title>Профиль</title>
            </Helmet>
            <div className={styles.header}>
                <div className={styles.header__login}>@cacaolover</div>
                <div className={styles.header__menu}>
                    <NavLink className={({isActive}) => cn(styles.header__menu__button, isActive && styles.header__menu__button__isActive)}  to={`/profile/${params.userId}/info`}>Информация</NavLink>
                    <NavLink className={({isActive}) => cn(styles.header__menu__button, isActive && styles.header__menu__button__isActive)}  to={`/profile/${params.userId}/missing`}>Объявления</NavLink>
                    <NavLink className={({isActive}) => cn(styles.header__menu__button, isActive && styles.header__menu__button__isActive)}  to={`/profile/${params.userId}/notification`}>Уведомления</NavLink>
                </div>
            </div>
            <div className={styles.profile}>
                <div className={styles.profile__left_bar}>
                    <div className={styles.profile__left_bar__image}></div>
                    <div className={styles.profile__left_bar__name}>{user?.firstName}</div>
                    <div className={styles.profile__left_bar__contact}>{user?.email}</div>
                    <div className={styles.profile__left_bar__contact}>+7 (917) 265 99 56</div>

                    {account?.id === params.userId? <button className={styles.profile__left_bar__button}
                             onClick={() => setVisible(true)}>Редактировать</button> : ""}
                </div>
                <div className={styles.profile__right_bar}>
                    <Outlet/>
                </div>
            </div>
        </>
    )
}




