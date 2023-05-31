import React from "react";
import {useAccount} from "../../store/AccountProvider";
import {useNavigate} from "react-router-dom";
import styles from "./authorization.module.css"
import {Formik} from "formik";
import {FcGoogle} from "react-icons/fc";
import {SlSocialVkontakte} from "react-icons/sl";


export const RegistrationForm = ({closeModal = (f) => f}) => {
    const {register} = useAccount()
    const navigate = useNavigate()
    let regError;

    return (<Formik
        initialValues={{username: '', email: '', password: ''}}
        validate={values => {
            const errors = {};
            if (!values.email) {
                errors.email = 'Обязательное поле';
            } else if (
                !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email)
            ) {
                errors.email = 'Неверная почта';
            }
            return errors;
        }}
        onSubmit={async (values, {setSubmitting}) => {
            let result = register({username: values.username, password: values.password, email: values.email})
            if (!result.error) {
                closeModal()
                navigate("/profile")
            } else {
                regError = result.error
            }
        }}
    >
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
                <input placeholder="Почта" className={styles.auth_container__form__input}
                       type="text"
                       name="email"
                       onChange={handleChange}
                       onBlur={handleBlur}
                       value={values.email}

                />
                <div className={styles.auth_container__form__error}>{errors.email && touched.email && errors.email}</div>
                <input placeholder="Никнейм" className={styles.auth_container__form__input}
                       type="text"
                       name="username"
                       onChange={handleChange}
                       onBlur={handleBlur}
                       value={values.username}
                />
                <input placeholder="Пароль" className={styles.auth_container__form__input}
                       type="password"
                       name="password"
                       onChange={handleChange}
                       onBlur={handleBlur}
                       value={values.password}
                />
                {regError}
                <div
                    className={styles.auth_container__form__error}>{errors.password && touched.password && errors.password}</div>
                <button className={styles.auth_container__form__button} type="submit" disabled={isSubmitting}>
                    Регистрация
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