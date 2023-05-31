import React from 'react'
import styles from './home.module.css'

export const SelectableBlock = ({selectFunc = (f) => f, image}) => {
    return(
        <div onMouseEnter={() => selectFunc(image)} className={styles.block}></div>
    )
}