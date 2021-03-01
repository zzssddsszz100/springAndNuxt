import axios from "axios";
import errorParser from '@/utils/error-parser'

export default {
  insertPendant (detail){
    return new Promise((resolve, reject) => {
      axios.post('/pendant', detail).then(({data}) => {
        resolve(data)
      }).catch(error => {
        reject(errorParser.parse(error))
      })
    })
  }
}
