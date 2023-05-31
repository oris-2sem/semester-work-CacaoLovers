import React from "react";
import {Helmet} from "react-helmet";

export const Shelter = ({title}) => {
    return (
        <div>
            <Helmet>
                <title>{title}</title>
            </Helmet>
            Shelters page
        </div>
    )
}