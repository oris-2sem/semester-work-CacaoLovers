import styles from "../profile.module.css";
import {useNavigate, useParams} from "react-router-dom";
import {useAccount} from "../../../store/AccountProvider";
import {useEffect, useState} from "react";


export const ProfileInfo = () => {
    const navigate = useNavigate()
    const {logout, getAccount} = useAccount()
    const params = useParams()
    const [user, setUser] = useState()

    useEffect(() => {
        async function getProfile() {
            let response = await getAccount(params.userId)
            response = await response.json()
            setUser(response)
        }
        getProfile()
    }, [])

    let exit = async () => {
        await logout()
        navigate("/")
    }


    const stringFromEpochMillis = (date) => {
        date = new Date(date)
        return date.getDate() + "/" + date.getMonth() + "/" + date.getFullYear()
    }


    return user && (
        <div className={styles.profile__right_bar__info}>
            <div className={styles.profile__right_bar__info__header}>Информация</div>
            <div className={styles.profile__right_bar__info__lines}>
                <div className={styles.profile__right_bar__info__lines__key}>Никнейм</div>
                <div className={styles.profile__right_bar__info__lines__value}>{user?.username}</div>
            </div>
            <div className={styles.profile__right_bar__info__lines}>
                <div className={styles.profile__right_bar__info__lines__key}>Город</div>
                <div className={styles.profile__right_bar__info__lines__value}>{user?.city?.name || "Неизвестен"}</div>
            </div>
            <div className={styles.profile__right_bar__info__lines} >
                <div className={styles.profile__right_bar__info__lines__key}>На сайте с</div>
                <div className={styles.profile__right_bar__info__lines__value}>{stringFromEpochMillis(user?.createdTime)}</div>
            </div>
            <div className={styles.profile__right_bar__info__lines}>
                <div className={styles.profile__right_bar__info__lines__key}>Последнее обновление</div>
                <div className={styles.profile__right_bar__info__lines__value}>{stringFromEpochMillis(user?.updatedTime)}</div>
            </div>
            <div className={styles.profile__right_bar__info__exit} onClick={exit}>
                <button>Выйти</button>
            </div>
        </div>
    )
}