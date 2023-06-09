import React, {useEffect, useState} from "react";
import {YMaps, Map} from "@pbe/react-yandex-maps";
import styles from './missing.module.css'
import {Helmet} from "react-helmet";
import {MissingEntity} from "../../components/MissingEntity/MissingEntity";
import {Link, useLocation, useNavigate, useParams} from "react-router-dom";
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
    const [missingList, setMissingList] = useState([])
    const location = useLocation()


    useEffect(() => {
        async function getMissings(type) {
            let response = await getMissing(type)
            response = await response.json()
            setMissingList(response)
        }
        getMissings(location.pathname.split('/')[2])
    }, [location])

    return(
        <>
            <Helmet>
                <title>{title}</title>
            </Helmet>

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
                        {missingList && missingList.map((missing) => {
                            return (<MissingEntity key={missing.id} missing={missing}/>)
                        })}
                    </div>
                    <Link className={styles.missing__create_button} to={`/missing/create/${location.pathname.split('/')[2]}/info`}>Создать объявление</Link>
                </div>
            </div>
        </>
    )
}
