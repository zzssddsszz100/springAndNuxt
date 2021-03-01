import _ from 'lodash'
import eventBus from '~/plugins/event-bus'

export default {
  parse (error) {
    if (error.response) {
      const status = error.response.status
      const data = error.response.data
      if (status === 400) {
        if (data && data.message) {
          return new Error(data.message)
        } else {
          return new Error('error.request.bad')
        }
      } else if (status === 401) {
        eventBus.$emit('user.unauthenticated')
        return new Error('error.request.notAuthorized')
      } else if (status === 403) {
        return new Error('error.request.forbidden')
      } else if (status === 404) {
        return new Error('error.request.notFound')
      } else if (status === 500) {
        if (data && data.message) {
          return new Error(data.message)
        } else {
          return new Error('error.request.unknownServerError')
        }
      } else {
        return new Error('error.request.failed')
      }
    } else if (error.request) {
      // Request was made and no response
      return new Error('error.request.noResponse')
    } else {
      return _.isError(error) ? error : new Error(error)
    }
  }
}
