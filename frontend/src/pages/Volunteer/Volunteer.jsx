import React, {useEffect, useState} from "react";
import {Helmet} from "react-helmet";
import styles from './volunteer.module.css'
import {useAccount} from "../../store/AccountProvider";
import {DistrictEditModule} from "./components/DistrictEditModule";
import {Modal} from "../../components/Modal/Modal";
import {Link, useNavigate} from "react-router-dom";




export const Volunteer = ({title}) => {
    let [districts, setDistricts] = useState([])
    let [visible, setVisible] = useState(false);
    let [render, setRender] = useState(false);
    let navigate = useNavigate();



    useEffect(() => {
        async function getDistricts() {
            let response = await fetch(`http://localhost:8080/volunteer/${localStorage.getItem("uuid")}`, {
                headers: {
                    "Authorization": localStorage.getItem("token")
                }
            })
            response = await response.json()
            setDistricts(response)
        }
        getDistricts()
    }, [render])


    return(

        <>
            <Helmet>
                <title>{title}</title>
            </Helmet>
            <Modal content={<DistrictEditModule/>} title="Выбор зон поиска" visible={visible} onClose={() => {setVisible(false); setRender(!render)}}></Modal>
            <div className={styles.content}>
                <div className={styles.content}>
                    <div className={styles.content__left_bar}>
                        {Object.keys(districts).map((district) => {
                            return (
                                <div className={styles.content__left_bar__district}>
                                    <div className={styles.content__left_bar__district__header}>{districts[district]?.district?.name}</div>
                                    <div className={styles.content__left_bar__district__info}>
                                        <div className={styles.content__left_bar__district__info__line}>
                                            <div className={styles.content__left_bar__district__info__line__key}>Всего пропаж</div>
                                            <div className={styles.content__left_bar__district__info__line__value}>{districts[district]?.missingAll}</div>
                                        </div>
                                        <div className={styles.content__left_bar__district__info__line}>
                                            <div className={styles.content__left_bar__district__info__line__key}>Пропаж сегодня</div>
                                            <div className={styles.content__left_bar__district__info__line__value}>{districts[district]?.missingToday}</div>
                                        </div>
                                        <div className={styles.content__left_bar__district__info__line}>
                                            <div className={styles.content__left_bar__district__info__line__key}>Активных волонтеров</div>
                                            <div className={styles.content__left_bar__district__info__line__value}>{districts[district]?.activeVolunteer}</div>
                                        </div>
                                        <div className={styles.content__left_bar__district__info__line}>
                                            <div className={styles.content__left_bar__district__info__line__key}>Пропажи</div>
                                        </div>
                                        {districts[district]?.missingList?.map((missing) => {
                                            return (<div onClick={() => navigate("/missing/lost")} className={styles.content__left_bar__district__info__missing}>{missing?.address}</div>)
                                        })}
                                    </div>
                                </div>
                            )
                        })}
                    </div>
                    <div className={styles.content__right_bar}>
                        <div className={styles.content__right_bar__zone_search}>
                            <div className={styles.content__right_bar__zone_search__header}>Зоны поиска</div>
                            <div className={styles.content__right_bar__zone_search__list}>
                                {Object.keys(districts).map((district) => {
                                    return (<div className={styles.content__right_bar__zone_search__list__district}>{districts[district]?.district?.name}</div>)
                                })}
                            </div>
                            <div className={styles.content__right_bar__zone_search__button} onClick={() => setVisible(true)}>Изменить</div>
                        </div>
                        <div className={styles.content__right_bar__zone_search}>
                            <div className={styles.content__right_bar__zone_search__header}>Показатели</div>
                            <div className={styles.content__right_bar__zone_search__list}>
                                <div className={styles.content__left_bar__district__info__line}>
                                    <div className={styles.content__left_bar__district__info__line__key}>Рейтинг</div>
                                    <div className={styles.content__left_bar__district__info__line__value}>0</div>
                                </div>
                                <div className={styles.content__left_bar__district__info__line}>
                                    <div className={styles.content__left_bar__district__info__line__key}>Найдено</div>
                                    <div className={styles.content__left_bar__district__info__line__value}>0</div>
                                </div>
                                <div className={styles.content__left_bar__district__info__line}>
                                    <div className={styles.content__left_bar__district__info__line__key}>Дней волонтерства</div>
                                    <div className={styles.content__left_bar__district__info__line__value}>1</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}