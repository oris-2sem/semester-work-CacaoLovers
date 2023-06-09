import styles from "../create.missing.module.css";
import React, {useEffect, useRef} from "react";
import {Link, useParams} from "react-router-dom";
import {useMissing} from "../../../store/MissingProvider";

export const MissingInfo = () => {

    const {missing} = useMissing();
    const param = useParams();

    missing.type = param.type === "lost" ? "LOST" : "FOUND"

    return (
        <div className={styles.info}>
            <div className={styles.info__header}>Основная информация</div>
            <form>
                <div className={styles.info__line}>
                    <div className={styles.info__line__key}>Вид питомца</div>
                    <input className={styles.info__line__value}
                           defaultValue={missing.kind}
                           type="text"
                           onChange={(e) => {missing.kind = e.target.value}}
                    />
                </div>

                <div className={styles.info__line}>
                    <div className={styles.info__line__key}>Пол</div>
                    <select defaultValue={missing.gender}
                            className={styles.info__line__value}
                            onChange={(e) => missing.gender = e.target.value}>
                        <option>Неопределен</option>
                        <option>Самец</option>
                        <option>Самка</option>
                    </select>
                </div>
                <div className={styles.info__line}>
                    <div className={styles.info__line__key}>Описание</div>
                    <textarea defaultValue={missing.description}
                              className={styles.info__line__value}
                              onChange={(e) => missing.description = e.target.value}/>
                </div>
                <div className={styles.info__line}>
                    <div className={styles.info__line__key}>Кличка</div>
                    <input defaultValue={missing.name}
                           className={styles.info__line__value}
                           type="text" onChange={(e) => missing.name = e.target.value}/>
                </div>
                <div className={styles.info__line}>
                    <Link to={`/missing/create/${param.type}/location`} className={styles.info__button}>Далее</Link>
                </div>
            </form>
        </div>
    )
}