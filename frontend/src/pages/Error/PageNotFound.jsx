import React from "react";
import {useLocation} from "react-router";
import {Helmet} from "react-helmet";
import styles from './page-not-found.module.css'

export const PageNotFound = ({title}) => {
    let location = useLocation()
    return (
        <div>
            <Helmet>
                <title>{title}</title>
            </Helmet>

            <div className={styles.error}>
                <div className={styles.error__image}/>
                <div className={styles.error__text}>
                    <div className={styles.error__text__header}>Упс..</div>
                    <div className={styles.error__text__body}>Страница {location.pathname} не найдена</div>
                </div>
            </div>
        </div>
    )
}