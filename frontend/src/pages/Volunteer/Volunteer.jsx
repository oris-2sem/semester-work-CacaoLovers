import React from "react";
import {Helmet} from "react-helmet";
import styles from './volunteer.module.css'

const districts = {
    "Вахитовский район": {
        missingAll: 155,
        missingToday: 13,
        activeVolunteer: 4,
        missings: [
            {
                id: 1,
                address: "Бондаренко 5"
            },
            {
                id: 2,
                address: "Бондаренко 65"
            },
            {
                id: 3,
                address: "Толстого 69"
            },
        ]
    },
    "Авиастроительный район": {
        missingAll: 15,
        missingToday: 13,
        activeVolunteer: 4,
        missings: [
            {
                id: 1,
                address: "Декабристов 54"
            },
            {
                id: 2,
                address: "Максимова 89"
            },
            {
                id: 3,
                address: "Толстого 69"
            },
        ]
    }
}


export const Volunteer = ({title}) => {
    return(
        <>
            <Helmet>
                <title>{title}</title>
            </Helmet>
            <div className={styles.content}>
                <div className={styles.content}>
                    <div className={styles.content__left_bar}>
                        {Object.keys(districts).map((district) => {
                            return (
                                <div className={styles.content__left_bar__district}>
                                    <div className={styles.content__left_bar__district__header}>{district}</div>
                                    <div className={styles.content__left_bar__district__info}>
                                        <div className={styles.content__left_bar__district__info__line}>
                                            <div className={styles.content__left_bar__district__info__line__key}>Всего пропаж</div>
                                            <div className={styles.content__left_bar__district__info__line__value}>{districts[district].missingAll}</div>
                                        </div>
                                        <div className={styles.content__left_bar__district__info__line}>
                                            <div className={styles.content__left_bar__district__info__line__key}>Пропаж сегодня</div>
                                            <div className={styles.content__left_bar__district__info__line__value}>{districts[district].missingToday}</div>
                                        </div>
                                        <div className={styles.content__left_bar__district__info__line}>
                                            <div className={styles.content__left_bar__district__info__line__key}>Активных волонтеров</div>
                                            <div className={styles.content__left_bar__district__info__line__value}>{districts[district].activeVolunteer}</div>
                                        </div>
                                        <div className={styles.content__left_bar__district__info__line}>
                                            <div className={styles.content__left_bar__district__info__line__key}>Пропажи</div>
                                        </div>
                                        {districts[district].missings.map((missing) => {
                                            return (<div className={styles.content__left_bar__district__info__missing}>{missing.address}</div>)
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
                                    return (<div className={styles.content__right_bar__zone_search__list__district}>{district}</div>)
                                })}
                            </div>
                            <div className={styles.content__right_bar__zone_search__button}>Изменить</div>
                        </div>
                        <div className={styles.content__right_bar__zone_search}>
                            <div className={styles.content__right_bar__zone_search__header}>Показатели</div>
                            <div className={styles.content__right_bar__zone_search__list}>
                                <div className={styles.content__left_bar__district__info__line}>
                                    <div className={styles.content__left_bar__district__info__line__key}>Рейтинг</div>
                                    <div className={styles.content__left_bar__district__info__line__value}>140</div>
                                </div>
                                <div className={styles.content__left_bar__district__info__line}>
                                    <div className={styles.content__left_bar__district__info__line__key}>Найдено</div>
                                    <div className={styles.content__left_bar__district__info__line__value}>140</div>
                                </div>
                                <div className={styles.content__left_bar__district__info__line}>
                                    <div className={styles.content__left_bar__district__info__line__key}>Дней волонтерства</div>
                                    <div className={styles.content__left_bar__district__info__line__value}>14</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}