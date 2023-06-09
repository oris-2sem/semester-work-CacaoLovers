import React, {useEffect, useRef, useState} from "react";
import styles from "../create.missing.module.css"
import {Link, useParams} from "react-router-dom";
import {useMissing} from "../../../store/MissingProvider";


export const MissingPhoto = () => {

    const {missing} = useMissing()
    const imageHolder = useRef(null)
    const [photo,returnPhoto] = useState()
    const param = useParams()

    let uploadPhotoFromForm = async (input) => {
        let data = new FormData()
        data.append('photo', input.files[0])
        let response = await fetch("http://localhost:8080/image", {
            headers: {
                Authorization: localStorage.getItem("token")
            },
            body: data,
            method: "POST"
        }).then()
        response = await response.json()
        missing.image.path = response.path
        missing.image.id = response.id
        returnPhoto(missing.image.path)
    }

    useEffect(() => {
        if (missing.image.path !== "search.png") imageHolder.current.style.backgroundImage = `url("http://localhost:8080/image/${missing.image.path}")`
    }, [photo])

    return (
        <>
        <div className={styles.info__header}>Фотографии</div>
        <div className={styles.photo}>
            <input type="file" className={styles.photo__button} ref={imageHolder}  onChange={(e) => uploadPhotoFromForm(e.target)}/>
        </div>
            <div className={styles.info__line} style={{marginTop: "60px"}}>
                <Link to={`/missing/create/${param.type}/location`} className={styles.info__button}>Назад</Link>
                <Link to={`/missing/create/${param.type}/verify`} className={styles.info__button}>Далее</Link>
            </div>
        </>
    )
}