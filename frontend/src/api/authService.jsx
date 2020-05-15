import Axios from "axios";
import * as HttpStatus from "http-status-codes";
const axios = Axios.create({
  baseURL: "http://192.168.0.94:8888/auth/",
  responseType: "application/json",
});

export async function post(apiPath, param = "") {
  return axios
    .post(apiPath, param)
    .then((res) => {
      if (res !== undefined && res === HttpStatus.OK) {
        return res;
      }
      return undefined;
    })
    .catch((error) => {
      if (error.response !== undefined) {
        console.log(
          `An Error occured doing a REST-Request ${error.response.status}`
        );
      }
      return undefined;
    });
}
