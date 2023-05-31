import React, {useEffect, useRef, useState} from 'react'
import styles from './modal.module.css'
import {createPortal} from "react-dom";
import {useModal} from "../../hooks/useModal";
import {Portal} from "../Portal/Portal";
import {GrClose} from "react-icons/gr"



export const Modal = ({visible, onClose, title, content}) => {
    const ref = useRef(null)
    const refButton = useRef(null)
    useModal(ref, () => onClose())
    useModal(refButton, () => onClose())
    return (
        <>
            {visible &&
            <Portal>
                <div className={styles.overlay} ref={ref}>
                    <div className={styles.modal}>
                        <div className={styles.modal__header}>
                            <div className={styles.modal__header__title}>{title}</div>
                            <div className={styles.modal__header__close} ref={refButton}></div>
                        </div>
                        <div className={styles.modal__content}>
                            {content}
                        </div>
                    </div>
                </div>
            </Portal>
            }
        </>
    )
}