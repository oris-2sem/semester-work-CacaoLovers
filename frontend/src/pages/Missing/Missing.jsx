import React, {useEffect, useState} from "react";
import {Modal} from "../../components/Modal/Modal";
import {YMaps, Map} from "@pbe/react-yandex-maps";
import styles from './missing.module.css'
import {Helmet} from "react-helmet";
import {catSearchOne, catSearchTwo, catSearchThree} from '../../image/index'
import {MissingEntity} from "../../components/MissingEntity/MissingEntity";
import {Link, useNavigate} from "react-router-dom";
import {useMissing} from "../../store/MissingProvider";



const mapState = {
    center: [55.753994, 37.622093],
    zoom: 9
};

const features = {
    "district": "Район",
    "description": "Описание",
    "owner": "Разместил"
}

const apiKey = 'fad4af17-6974-40f6-9835-1481bab2a7ad'

export const Missing = ({title}) => {
    const {getMissing} = useMissing();
    const [visibleModal, setVisibleModal] = useState(false)
    const [titleModal, setTitleModal] = useState("Объявление")
    const [contentModal, setContentModal] = useState(<div/>)
    const [missingList, setMissingList] = useState([])

    console.log(missingList)
    const setModal = (title, content) => {
        setVisibleModal(true)
        setTitleModal(title)
        setContentModal(content)
    }

    useEffect(() => {
        async function getMissings() {
            let response = await fetch(`http://localhost:8080/missing/list`, {
                headers: {
                    "Authorization": localStorage.getItem("token")
                },
                method: "GET"
            })
            response = await response.json()
            setMissingList(response)
        }
        getMissings()
    }, [])

    return(
        <>
            <Helmet>
                <title>{title}</title>
            </Helmet>

            <Modal title={titleModal}
                   content={contentModal}
                   visible={visibleModal}
                   onClose={() => {setVisibleModal(false)}}/>

            <div className={styles.map_container}>
                <YMaps query={{ apikey: apiKey }}>
                    <Map style={{width: '100%', height: '100%', display: 'block'}}
                         defaultState={{ center: mapState.center, zoom: 9 }}
                         modules={["Placemark", "geocode", "geoObject.addon.balloon"]}/>
                </YMaps>
                <div className={styles.missing}>
                    <div className={styles.missing__filter}>
                        <div className={styles.missing__filter__display}><p>Казань, активен, Московский район..</p></div>
                        <div className={styles.missing__filter__button}>Фильтр</div>
                    </div>
                    <div className={styles.missing__container}>
                        {missingList.map((missing) => {
                            return (<MissingEntity key={missing.id}
                                                   onClickMissing={() => setModal("Oбъявление " + missing.id, <MissingModalEntity missing={missing}/>)}
                                                   missing={missing}/>)
                        })}
                    </div>
                    <Link className={styles.missing__create_button} to={"/missing/create/info"}>Создать объявление</Link>
                </div>
            </div>
        </>
    )
}

const MissingModalEntity = ({missing}) => {
    const navigate = useNavigate();

    let deleteMissing = async function  {
        let response = await fetch(`http://${BASE_URI}:8080/missing/${missing?.id}`, {
            headers: {
                "Authorization": localStorage.getItem("token"),
            },
            method: "DELETE"
        })
    }
    return (
        <div className={styles.modal}>
            <div className={styles.modal__left}>
                <div className={styles.modal__left__image} style={{backgroundImage: `url("http://localhost:8080/image/${missing?.imagePath}")`}}></div>
            </div>
            <div className={styles.modal__right}>

                    <div className={styles.modal__right__line}>
                            <div className={styles.modal__right__line__key}>Разместил</div>
                            <div className={styles.modal__right__line__value}>{missing?.owner?.firstName}</div>
                    </div>
                    <div className={styles.modal__right__line}>
                        <div className={styles.modal__right__line__key}>Район</div>
                        <div className={styles.modal__right__line__value}>{missing?.district?.name}</div>
                    </div>
                    <div className={styles.modal__right__line}>
                        <div className={styles.modal__right__line__key}>Описание</div>
                        <div className={styles.modal__right__line__value}>{missing?.description}</div>
                    </div>

            </div>

            <button onClick={() => deleteMissing()}>Удалить</button>
        </div>
    )
}