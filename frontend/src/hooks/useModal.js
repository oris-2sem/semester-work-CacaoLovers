import React, {useEffect} from 'react'


export const useModal = (ref, handler) => {
    useEffect(() => {
            const reference = ref?.current

            const listener = (e) => {
                if (reference === e.target) handler()
            }

            reference?.addEventListener("mousedown", listener)

            return () => {
                reference?.removeEventListener("mousedown", listener)
            }

        }, [ref, handler]
    )
}