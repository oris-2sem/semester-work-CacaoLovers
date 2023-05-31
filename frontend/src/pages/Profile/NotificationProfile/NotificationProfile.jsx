import styles from "../profile.module.css";
import {Notification} from "../component/Notification";
import React from "react";
import {catSearchOne, catSearchThree, catSearchTwo} from "../../../image";

const notifications = [
    {
        id: "ffgf-gfdgd-gfdgdf-gdfd",
        title: "Собака",
        owner: {
            id: "bgfb-gfdf-gdfd",
            name: "Boris"
        },
        message: "найдена собака",
        date: "2022-11-22",
        time: "11:22",
        missing: {
            id: 1,
            city: 'Kazan',
            info: 'Потерялася собачка',
            image: catSearchOne
        }
    },
    {
        id: "ffgd-gfdgd-gfdgdf-gdfd",
        title: "Кошка",
        owner: {
            id: "bgfs-gfdf-gdfd",
            name: "Влад влад"
        },
        message: "найдена кошка",
        date: "2022-11-22",
        time: "12:22",
        missing: {
            id: 2,
            city: 'Kazan',
            info: 'Потерялася собачка',
            image: catSearchThree
        }
    },
    {
        id: "ffgо-gfdgd-gfdgdf-gdfd",
        title: "Енот",
        owner: {
            id: "bgfs-gfdf-gdfd",
            name: "Влад влад"
        },
        message: "найден енот",
        date: "2022-11-21",
        time: "12:22",
        missing: {
            id: 3,
            city: 'Kazan',
            info: 'Потерялася собачка',
            image: catSearchTwo
        }
    },
]



export const NotificationProfile = () => {

    let groupingArrayByData = (notifications, key) => {
        return notifications.reduce(function(rv, x) {
            (rv[x[key]] = rv[x[key]] || []).push(x);
            return rv;
        }, {});
    }

    let notificationArray = groupingArrayByData(notifications, 'date')

    return (
        <>
            {Object.keys(notificationArray).map((date) => {
                return (<div>
                    <div className={styles.notification_block}>
                        <div className={styles.notification_block__date}> {date} </div>
                        {notificationArray[date].map((notification) => (<Notification notification={notification}/>))}
                    </div>
                </div>)
            })
            }
        </>
    )
}