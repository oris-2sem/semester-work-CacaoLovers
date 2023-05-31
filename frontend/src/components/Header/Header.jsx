import React, {useContext, useState} from 'react'
import styles from './Header.module.css'
import {Link} from "react-router-dom";
import {logo} from '../../image'
import {Modal} from "../Modal/Modal";
import {useAccount} from "../../store/AccountProvider";
import {Authorization} from "../Authorization/Authorization";

export const Header = () => {
    const {auth, account} = useAccount()
    const [visible, setVisible] = useState(false)
    const [title, setTitle] = useState(<div>Aвторизация</div>)
    return (
        <>
            <Modal visible={visible} onClose={() => setVisible(false)} title={title} content={<Authorization setTitle={setTitle} closeModal = {() => setVisible(false)}/>}></Modal>
            <div className={styles.header_fix}></div>
            <div className={styles.header}>
                <div className={styles.header__logo}>
                    <img className={styles.header__logo__image} src={logo}/>
                    <Link className={styles.header__logo__link} to={""}>
                        <div><b>Pet</b>Home</div>
                    </Link>
                </div>
                <nav className={styles.header__link_container}>
                    <Link className={styles.header__link_container__link} to="missing/lost">Потерял</Link>
                    <Link className={styles.header__link_container__link} to="missing/found">Нашел</Link>
                    <Link className={styles.header__link_container__link} to="volunteer">Волонтерство</Link>
                    <Link className={styles.header__link_container__link} to="shelter">Приюты</Link>
                </nav>

                {auth ? (<Link to={`/profile/${localStorage.getItem("uuid")}/info`} className={styles.header__auth}>{account.username}</Link>) :  (<div className={styles.header__auth} onClick={() => setVisible(true)}>Войти</div>) }
            </div>
        </>
    )
}


