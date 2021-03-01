import { isAuthGuardActive } from '../constants/config'
import { setCurrentUser, getCurrentUser } from '.'
export default (to, from, next) => {
  if (to.matched.some(record => record.meta.loginRequired)) {
    if (isAuthGuardActive) {
      const user = getCurrentUser();
      if (user) {
        const roleArrayHierarchic = to.matched.filter(x => x.meta.roles).map(x => x.meta.roles);
        let auth = false;
        for (let v of user.role){
          if (roleArrayHierarchic[0].indexOf(v) > -1) auth = true
        }
        if(auth){
          next()
        }else {
          next('/unauthorized')
        }
      } else {
        setCurrentUser(null);
        next('/user/login')
      }
    } else {
      next();
    }
  } else {
    next()
  }
}
