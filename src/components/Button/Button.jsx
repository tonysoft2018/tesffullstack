import '../../theme/Button/Button.css'

export const Button = (props) => {
    const { style, type = 'button', text, handleClick } = props;
    return (
        <>
            <button 
                type={ type }
                className='btn'
                style={ style }
                onClick={ handleClick }
            >
                { text }
            </button>
        </>
    )
}