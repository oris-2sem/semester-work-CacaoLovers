import React from "react";
import styles from './notification.module.css'




export const Notification = ({notification}) => {
    return (
        <div className={styles.notification}>
            <div className={styles.notification__image} style={{backgroundImage: `url(${notification.missing.image})`}}></div>
            <div className={styles.notification__info}>
                <div className={styles.notification__info__left}>
                    <div className={styles.notification__info__left__header}>{notification.missing.info}</div>
                    <div className={styles.notification__info__left__action}>{notification.message}</div>
                </div>
                <div className={styles.notification__info__right}>
                    <div className={styles.notification__info__right__name}>{notification.owner.name}</div>
                    <div className={styles.notification__info__right__time}>{notification.time}</div>
                </div>
            </div>
        </div>
    )
}