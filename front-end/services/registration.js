import axios from 'axios'
import errorParser from '@/utils/error-parser'

export default {
  /**
   * 회원가입
   * @param {Object} 회원가입 입력정보
   */
  register (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/api/registrations', detail).then(({data}) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}
