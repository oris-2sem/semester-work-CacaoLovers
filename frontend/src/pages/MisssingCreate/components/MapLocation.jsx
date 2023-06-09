import {Map, useYMaps} from "@pbe/react-yandex-maps";
import React, {useRef, useState} from "react";
import styles from "../create.missing.module.css";
import {Link, useParams} from "react-router-dom";
import {useMissing} from "../../../store/MissingProvider";

export const MapLocation = () => {

    const param = useParams()

    const {missing} = useMissing()
    const location = useRef(null)

    const mapState = {
        center: [55.753994, 37.622093],
        zoom: 9
    };
    let ymaps = useYMaps(['Map']);
    let placemarkRef = useRef(null);
    let mapRef = useRef(null);
    let [address, setAddress] = useState("");

    const createPlacemark = (coords) => {
        return new ymaps.Placemark(
            coords,
            {
                iconCaption: "loading.."
            },
            {
                preset: "islands#violetDotIconWithCaption",
                draggable: true
            }
        );
    };

    const getAddress = (coords) => {
        placemarkRef.current.properties.set("iconCaption", "loading..");
        let newAddress;
        ymaps.geocode(coords).then((res) => {
            const firstGeoObject = res.geoObjects.get(0);

            newAddress = [
                firstGeoObject.getLocalities().length
                    ? firstGeoObject.getLocalities()
                    : firstGeoObject.getAdministrativeAreas(),
                firstGeoObject.getThoroughfare() || firstGeoObject.getPremise()
            ]
                .filter(Boolean)
                .join(", ")
            // .split(',');

            setAddress(newAddress);
            missing.address = newAddress
            missing.posX = coords[0]
            missing.posY = coords[1]
            location.current.value = newAddress

            let difAddress = newAddress.split(',')
            missing.district.name = difAddress[1]
            missing.district.city = difAddress[0]
            missing.address = newAddress

            placemarkRef.current.properties.set({
                iconCaption: newAddress,
                balloonContent: firstGeoObject.getAddressLine()
            });
        });

    };

    const onMapClick = (e) => {
        const coords = e.get("coords");

        if (placemarkRef.current) {
            placemarkRef.current.geometry.setCoordinates(coords);
        } else {
            placemarkRef.current = createPlacemark(coords);
            mapRef.current.geoObjects.add(placemarkRef.current);
            placemarkRef.current.events.add("dragend", function () {
                getAddress(placemarkRef.current.geometry.getCoordinates());
            });
        }
        getAddress(coords);
    };
    return (
        <>
            <Map className={styles.location__map} defaultState={{ center: mapState.center, zoom: 9 }}
                 modules={["Placemark", "geocode", "geoObject.addon.balloon"]}
                 instanceRef={mapRef}
                 onClick={onMapClick}
                 state={mapState}
            />
            <div className={styles.location__address}>
                <div>Адрес</div>
                <input ref={location} defaultValue={missing.address}/>
            </div>
            <div className={styles.info__line}>
                <Link to={`/missing/create/${param.type}/info`} className={styles.info__button}>Назад</Link>
                <Link to={`/missing/create/${param.type}/photo`} className={styles.info__button}>Далее</Link>
            </div>
        </>
    );
}