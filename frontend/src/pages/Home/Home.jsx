import React, {useEffect, useState} from 'react';
import styles from "./home.module.css";
import {SelectableBlock} from "./SelectableBlock"
import {catOne, catTwo, dogOne, dogTwo} from '../../image'
import {Helmet} from "react-helmet";
import {Modal} from "../../components/Modal/Modal";
import {Authorization} from "../../components/Authorization/Authorization";

const backgroundImage = [
    catOne,
    dogOne,
    catTwo,
    dogTwo
];

export const Home = ({title, auth}) => {
    const [background, setBackground] = useState(dogOne)
    const [visibleAuth, setVisibleAuth] = useState(false)

    const setBackgroundContainer = (image) => {
        setBackground(image)
    }

    useEffect(() => {
            if (auth !== undefined) setVisibleAuth(!auth)
        }, [])
    return (
        <>
            <Helmet>
                <title>{title}</title>
            </Helmet>

            { auth || (<Modal visible={visibleAuth} onClose={() => setVisibleAuth(false)} title={"Aвторизация"} content={<Authorization closeModal = {() => setVisibleAuth(false)}/>}></Modal>)}

            <div className={styles.content_container}>
                <div className={styles.container} style={{backgroundImage: `url(${background})`}}>
                    {
                        backgroundImage.map((background, index) => {
                            return (<SelectableBlock key={index} selectFunc={setBackgroundContainer} image={background}/>)
                        })
                    }
                </div>
                <div className={styles.text}>Ищем ваших питомцев</div>
            </div>
        </>
    )
}
