
import React, { useState } from 'react';
import axios from 'axios';

const FileUpload = (props) => {

    const [file, setFile] = useState('');
    const [filename, setFilename] = useState('Choose File');
    const [errorMsg, setError] = useState('')
    const [uploadedFile, setUploadedFile] = useState({});
    const [message, setMessage] = useState('');
    const [uploadPercentage, setUploadPercentage] = useState(0);

    const onFileSelected = (event) => {
        setFile(event.target.files[0])
        setFilename(event.target.files[0].name)
    }

    const upload = async (event) => {
        event.preventDefault()
        const formData = new FormData()
        formData.append('file', file)

        try {
            const res = await axios.post("http://localhost:8080/jar", formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })

            props.present(false)
            props.next(true)
        } catch (error) {
            setError("You can only upload JAR files!")
        }

    }

    return (
        <div>
            <input
                className='button button1'
                type="file"
                onChange={onFileSelected}
            ></input>
            <br />
            {file ?
                <button className='button button1' onClick={upload}>Upload <b>{filename}</b></button> : null
            }
            {errorMsg ?
                <p className='errorText'>{errorMsg}</p> : null
            }
        </div>
    )
}

export default FileUpload