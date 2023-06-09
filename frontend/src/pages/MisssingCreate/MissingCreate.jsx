import React, {useEffect, useRef, useState} from "react";
import styles from './create.missing.module.css'
import {Helmet} from "react-helmet";
import {useAccount} from "../../store/AccountProvider";
import {NavLink, useNavigate, useParams} from "react-router-dom";
import {Map, useYMaps, YMaps} from "@pbe/react-yandex-maps"
import {Outlet} from "react-router-dom"
import cn from "classnames"
import {MissingProvider} from "../../store/MissingProvider";

export const MissingCreate = ({title}) => {
    const {auth} = useAccount()
    const navigate = useNavigate()
    const param = useParams();
    useEffect(() => {
        if (!auth) return navigate("/auth")
    })
    return(
        <MissingProvider>
            <Helmet>
                <title>{title}</title>
            </Helmet>

            <div className={styles.container}>
                <div className={styles.container__left_bar}>
                    <NavLink to={`/missing/create/${param.type}/info`} className={({isActive}) => cn(styles.container__left_bar__step, isActive && styles.container__left_bar__step__isActive)}>Основная информация</NavLink>
                    <NavLink to={`/missing/create/${param.type}/location`}  className={({isActive}) => cn(styles.container__left_bar__step, isActive && styles.container__left_bar__step__isActive)}>Локация</NavLink>
                    <NavLink to={`/missing/create/${param.type}/photo`}  className={({isActive}) => cn(styles.container__left_bar__step, isActive && styles.container__left_bar__step__isActive)}>Фотографии</NavLink>
                    <NavLink to={`/missing/create/${param.type}/verify`}  className={({isActive}) => cn(styles.container__left_bar__step, isActive && styles.container__left_bar__step__isActive)}>Подтверждение</NavLink>
                </div>

                <div className={styles.container__right_bar}>
                    <YMaps query={{ apikey: 'fad4af17-6974-40f6-9835-1481bab2a7ad' }}>
                        <Outlet/>
                    </YMaps>
                </div>
            </div>
        </MissingProvider>
    )
}


