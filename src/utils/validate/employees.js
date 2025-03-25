import * as Yup from 'yup';




export function validateForms() {
    return Yup.object({
        nombre: Yup.string().required(true),
        apellido: Yup.string().required(true),
        fechanac: Yup.string().required(true),
        puesto: Yup.string().required(true),
        sueldo: Yup.string().required(true)
    })
}