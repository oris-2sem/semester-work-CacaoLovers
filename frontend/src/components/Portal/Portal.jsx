import React, {useEffect, useState} from 'react'
import {createPortal} from "react-dom";

export const Portal = ({children}) => {
    const [container] = useState(document.createElement("div"))
    container.classList.add("portal-root")

    useEffect(() => {
        document.body.appendChild(container)

        return () => {
            document.body.removeChild(container)
        }
    }, [])

    return createPortal(children, container)
}