import '../../theme/TextField/TextField.css';

export const TextField = (props) => {
    const { style, name, value, errors, type = 'text', placeholder, handleChange, marginTop } = props;

    return (
        <div className={errors ? "container-input-error" : "container-input"} style={{ marginTop: marginTop}}>
            <input 
                name={ name }
                type={ type }
                placeholder={ placeholder }
                value={ value }
                onChange={ handleChange }
                style={{
                    ...style,
                    
                }}
            />
            {
                errors && <label className="error-label">El campo es requerido</label>
            }
            
        </div>
    )
}