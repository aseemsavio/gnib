import axios from "axios"
import React, { useState } from "react"

const Build = (props) => {

    const [building, setBuilding] = useState(false)
    const [buildComplete, setBuildComplete] = useState(false)

    const initiateBuild = () => {
        setBuilding(true)
        axios.post("http://localhost:8080/build/native-image")
            .then(res => {
                // todo: do something with the reponse.
                setBuilding(false)
                setBuildComplete(true)
            })
    }

    const onDownloadButtonCLicked = () => {
        props.present(false)
        props.next(true)
    }

    return (
        <div>
            {!buildComplete ?
                <button
                    className="button button1"
                    onClick={initiateBuild}
                >
                    Build Native Image
                </button> : null
            }
            <br />
            {building && !buildComplete ?
                <>
                    <p>Building Native Image...</p>
                    <small>Logs will appear here</small>
                </>
                : null
            }

            {buildComplete ?
                <button
                    className="button button1"
                    onClick={onDownloadButtonCLicked}
                >
                    Download Result
                </button> : null
            }

        </div>
    )
}

export default Build

