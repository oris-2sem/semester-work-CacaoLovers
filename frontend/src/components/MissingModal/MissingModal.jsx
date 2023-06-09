import {useAccount} from "../../store/AccountProvider";
import styles from "./missing.modal.module.css";
import {Link, useNavigate} from "react-router-dom";
import React from "react";

export const MissingModal = ({missing}) => {

    const {account} = useAccount();
    const navigate = useNavigate()

    let deleteMissing = async function () {
        await fetch(`http://localhost:8080/missing/${missing?.id}`, {
            headers: {
                "Authorization": localStorage.getItem("token"),
            },
            method: "DELETE"
        })
        navigate(`/missing/${missing.type}`)
    }

    let responseMissing = async function () {

        await fetch(`http://localhost:8080/missing/response`, {
            headers: {
                "Authorization": localStorage.getItem("token"),
                "Content-Type": "application/json"
            },
            method: "PUT",
            body: JSON.stringify({
                "accountId": account?.id,
                "missingId": missing?.id
            })
        })
        navigate(`/missing/${missing.type}`)
    }

    return (
        <div className={styles.modal}>
            <div className={styles.modal__left}>
                <div className={styles.modal__left__image} style={{backgroundImage: `url("http://localhost:8080/image/${missing?.imagePath}")`}}></div>
            </div>
            <div className={styles.modal__right}>

                <div className={styles.modal__right__line}>
                    <div className={styles.modal__right__line__key}>Разместил</div>
                    <Link to={`/profile/${missing?.owner?.id}/info`} className={styles.modal__right__line__value}>{missing?.owner?.firstName}</Link>
                </div>
                <div className={styles.modal__right__line}>
                    <div className={styles.modal__right__line__key}>Район</div>
                    <div className={styles.modal__right__line__value}>{missing?.district?.name}</div>
                </div>
                <div className={styles.modal__right__line}>
                    <div className={styles.modal__right__line__key}>Описание</div>
                    <div className={styles.modal__right__line__value}>{missing?.description}</div>
                </div>
                <div className={styles.modal__right__line}>
                    <div className={styles.modal__right__line__key}>Вид</div>
                    <div className={styles.modal__right__line__value}>{missing?.kind}</div>
                </div>
                <div className={styles.modal__right__line}>
                    <div className={styles.modal__right__line__key}>Пол</div>
                    <div className={styles.modal__right__line__value}>{missing?.gender}</div>
                </div>
                <div className={styles.modal__right__line}>
                    <div className={styles.modal__right__line__key}>Адрес</div>
                    <div className={styles.modal__right__line__value}>{missing?.address}</div>
                </div>
                <div className={styles.modal__right__button_holder}>
                    { missing.type === 'FOUND' && missing.owner?.id !== account.id ? <button onClick={responseMissing}>Мой питомец</button> : ""}
                    { missing.type === 'LOST' && missing.owner?.id !== account.id  ? <button onClick={responseMissing}>Нашел</button> : ""}
                    { missing.owner?.id === account.id ? <button onClick={() => deleteMissing()}>Удалить</button> : <button >Жалоба</button>}
                </div>
            </div>

        </div>
    )
}