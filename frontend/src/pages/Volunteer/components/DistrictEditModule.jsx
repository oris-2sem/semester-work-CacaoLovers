import React, {useEffect, useState} from 'react'
import styles from './district.edit.module.css'

export const DistrictEditModule = () => {
    const [selectDistrict, setSelectDistrict] = useState([])
    const [unselectDistrict, setUnSelectDistrict] = useState([])
    const [render, setRender] = useState(false);

    useEffect(() => {
        async function getDistrict() {
            let userId = localStorage.getItem("uuid");
            let auth = localStorage.getItem("token")
            let responseSelect = await fetch(`http://localhost:8080/volunteer/select/${userId}`, {
                headers:{
                    "Authorization": auth
                }
            })
            let responseUnSelect = await fetch(`http://localhost:8080/volunteer/unselect/${userId}`, {
                headers:{
                    "Authorization": auth
                }
            })
            responseSelect = await responseSelect.json();
            responseUnSelect = await responseUnSelect.json();
            setSelectDistrict(responseSelect)
            setUnSelectDistrict(responseUnSelect)
        }

        getDistrict()
    },[render])

    async function addUnselectDistrict (districtId) {
        let userId = localStorage.getItem("uuid");
        let auth = localStorage.getItem("token")
        await fetch(`http://localhost:8080/volunteer`, {
            headers:{
                "Authorization": auth,
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "accountId": userId,
                "districtId": districtId
            }),
            method: "POST"
        })
        setRender(!render)
    }

    async function deleteSelectDistrict (districtId) {
        let userId = localStorage.getItem("uuid");
        let auth = localStorage.getItem("token")
        await fetch(`http://localhost:8080/volunteer`, {
            headers:{
                "Authorization": auth,
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "accountId": userId,
                "districtId": districtId
            }),
            method: "DELETE"
        })
        setRender(!render)
    }

    return (
        <div className={styles.container}>
            <div className={styles.container__header}>Текущие районы</div>
            <div className={styles.container__districts}>
                {selectDistrict.map((district) => {
                    return <DistrictSelect onClickDistrict = {() => deleteSelectDistrict(district.id)} district={district}/>
                })}
            </div>
            <div className={styles.container__header}>Доступные районы</div>
            <div className={styles.container__districts}>
                {unselectDistrict.map((district) => {
                    return <DistrictSelect onClickDistrict = {() => addUnselectDistrict(district.id)} district={district}/>
                })}
            </div>
            <button></button>
        </div>
    )
}

export const DistrictSelect = ({district, onClickDistrict = (f) => f}) => {
    return (
        <div onClick={onClickDistrict} className={styles.container__districts__entity}>
            {district.name}
        </div>
    )
}