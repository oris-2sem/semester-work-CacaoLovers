import React, {useRef, useState} from "react";
import styles from "../create.missing.module.css"
import {useMissing} from "../../../store/MissingProvider";
import {useEffect} from "react";
import {useParams} from "react-router-dom";


export const MissingVerify = () => {
    const {missing, addMissing} = useMissing();
    const {photo, setPhoto} = useState()
    const imageHolder = useRef(null)

    useEffect(() => {
        if (missing.image.path) imageHolder.current.style.backgroundImage = `url("http://localhost:8080/image/${missing.image.path}")`
    }, [photo])


    return(
        <>
            <div className={styles.verify}>
                <div ref={imageHolder} className={styles.verify__photo}/>
                <div className={styles.verify__info}>
                    <div className={styles.verify__info__lines}>
                        <div className={styles.verify__info__lines__key}>Кличка</div>
                        <div className={styles.verify__info__lines__value}>{missing.name}</div>
                    </div>
                    <div className={styles.verify__info__lines}>
                        <div className={styles.verify__info__lines__key}>Вид</div>
                        <div className={styles.verify__info__lines__value}>{missing.kind}</div>
                    </div>
                    <div className={styles.verify__info__lines}>
                        <div className={styles.verify__info__lines__key}>Пол</div>
                        <div className={styles.verify__info__lines__value}>{missing.gender}</div>
                    </div>
                    <div className={styles.verify__info__lines}>
                        <div className={styles.verify__info__lines__key}>Описание</div>
                        <div className={styles.verify__info__lines__value}>{missing.description}</div>
                    </div>
                    <div className={styles.verify__info__lines}>
                        <div className={styles.verify__info__lines__key}>Локация</div>
                        <div className={styles.verify__info__lines__value}>{missing.address}</div>
                    </div>
                </div>
            </div>
            <div className={styles.info__line}>
                <button className={styles.info__button} onClick={() => addMissing()}>Разместить объявление</button>
            </div>
    </>
    )
}