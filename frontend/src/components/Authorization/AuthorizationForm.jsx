import React from "react";
import styles from "./authorization.module.css"
import {FcGoogle} from "react-icons/fc";
import {SlSocialVkontakte} from "react-icons/sl";
import {Formik} from "formik";
import {useAccount} from "../../store/AccountProvider";
import {useNavigate} from "react-router-dom";

export const AuthorizationForm = ({closeModal = (f) => f}) => {

    const {login} = useAccount()
    const navigate = useNavigate()
    let authError;

    return (<Formik
        initialValues={{username: '', password: ''}}
        validate={values => {
            const errors = {};
            if (!values.username) {
                errors.username = 'Обязательное поле';
            } else if (values.username.length < 5) {
                errors.username = 'Ник должен содержать минимум 5 символов';
            }
            return errors;
        }}
        onSubmit={async (values, {setSubmitting}) => {
            let result = await login(values.username, values.password)
            if (!result.error) {
                closeModal()
                navigate(`/profile/${localStorage.getItem("uuid")}/info`)
            } else {
                authError = result.error
            }
        }}>
        {({
              values,
              errors,
              touched,
              handleChange,
              handleBlur,
              handleSubmit,
              isSubmitting,
              /* and other goodies */
          }) => (
            <form onSubmit={handleSubmit} className={styles.auth_container__form}>
                <input placeholder="Никнейм" className={styles.auth_container__form__input}
                       type="text"
                       name="username"
                       onChange={handleChange}
                       onBlur={handleBlur}
                       value={values.email}

                />
                <div className={styles.auth_container__form__error}>{errors.username && touched.username && errors.username}</div>
                <input placeholder="Пароль" className={styles.auth_container__form__input}
                       type="password"
                       name="password"
                       onChange={handleChange}
                       onBlur={handleBlur}
                       value={values.password}
                />
                <div
                    className={styles.auth_container__form__error}>{errors.password && touched.password && errors.password}</div>
                {authError}
                <button className={styles.auth_container__form__button} type="submit" disabled={isSubmitting}>
                    Авторизация
                </button>

                <div className={styles.auth_container__form__oauth}>
                    <div className={styles.auth_container__form__oauth__entity}> Войти с помощью</div>
                    <div className={styles.auth_container__form__oauth__entity}><FcGoogle size={20}
                                                                                          style={{cursor: "pointer"}}/>
                    </div>
                    <div className={styles.auth_container__form__oauth__entity}><SlSocialVkontakte size={20}
                                                                                                   style={{cursor: "pointer"}}/>
                    </div>

                </div>
            </form>
        )}
    </Formik>)
}