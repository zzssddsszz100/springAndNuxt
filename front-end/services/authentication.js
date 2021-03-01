import axios from 'axios'
import errorParser from '@/utils/error-parser'

export default {
  /**
   * 로그인유저
   * @param detail {Object} 로그인유저 아이디, 비밀번호
   */
  login (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/api/authentications', detail).then(({data}) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}
