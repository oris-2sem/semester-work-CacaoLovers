import React, {useState} from "react";
import styles from './missing.entity.module.css'
import {Modal} from "../Modal/Modal";
import {MissingModal} from "../MissingModal/MissingModal";



export const MissingEntity = ({onClickMissing = (f) => f, missing}) => {

    const [visible, setVisible] = useState(false)


    return (
        <>
            <Modal title={`Объявление ${missing.name}`}
                   content={<MissingModal missing={missing}/>}
                   visible={visible}
                   onClose={() => setVisible(false)}/>

            <div onClick={() => setVisible(true)} className={styles.entity}>
                <div className={styles.entity__photo} style={{backgroundImage: `url("http://localhost:8080/image/${missing?.imagePath}")`}}/>
                <div className={styles.entity__info}>
                    <div className={styles.entity__info__header}>
                        <div className={styles.entity__info__header__name}>{missing?.name}</div>
                        <div className={styles.entity__info__header__status}>активен</div>
                    </div>
                    <div className={styles.entity__info__body}>
                        <div className={styles.entity__info__body__line}>
                            <div className={styles.entity__info__body__line__key}>Разместил</div>
                            <div className={styles.entity__info__body__line__value}>{missing?.owner?.firstName}</div>
                        </div>
                        <div className={styles.entity__info__body__line}>
                            <div className={styles.entity__info__body__line__key}>Район</div>
                            <div className={styles.entity__info__body__line__value}>{missing?.district?.name}</div>
                        </div>
                        <div className={styles.entity__info__body__line}>
                            <div className={styles.entity__info__body__line__key}>Описание</div>
                            <div className={styles.entity__info__body__line__value}>{missing?.description}</div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}