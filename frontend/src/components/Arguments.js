import React, { useState } from "react"
import axios from 'axios';

const Arguments = (props) => {

    const [compilationArgs, setArguments] = useState("")
    const [compilationArgsResp, setArgumentsResp] = useState("")

    const handleChange = (event) => {
        setArguments(event.target.value)
    }

    const sendArguments = () => {
        axios.post("http://localhost:8080/native-image/arguments", compilationArgs)
            .then(resp => {
                setArgumentsResp(resp.data)
                props.present(false)
                props.next(true)
            })
    }

    return (
        <>
            <textarea
                placeholder='Enter your custom "native-image .." command if any'
                className='textarea'
                onChange={handleChange}
            />
            <button
                className='button button1'
                onClick={sendArguments}
            >Apply Arguments</button>
            {compilationArgsResp ?
                <p>{compilationArgsResp}</p> : null
            }
        </>
    )
}

export default Arguments
