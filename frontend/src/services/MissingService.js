const BASE_URI = "localhost"

export default class MissingService {

    static async uploadPhotoMissing (input){
        let data = new FormData()
        data.append('photo', input.files[0])
        return await fetch("http://localhost:8080/image", {
            headers: {
                Authorization: localStorage.getItem("token")
            },
            body: data,
            method: "POST"
        }).then()
    }

    static async addMissing(missing){
        return await fetch(`http://${BASE_URI}:8080/missing`, {
            headers: {
                "Authorization": localStorage.getItem("token"),
                "Content-Type": "application/json"
            },
            body: JSON.stringify(missing),
            method: "POST"
        })
    }

    static async deleteMissing(id){
        return await fetch(`http://${BASE_URI}:8080/missing/${id}`, {
            headers: {
                "Authorization": localStorage.getItem("token"),
            },
            method: "DELETE"
        })
    }

    static async getMissing(){
        return await fetch(`http://${BASE_URI}:8080/missing/list`, {
            method: "GET"
        })
    }
}