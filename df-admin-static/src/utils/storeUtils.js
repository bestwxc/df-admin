import Cookies from 'js-cookie'

function setCookie (key, value) {
  Cookies.set(key, JSON.stringify(value))
}

function getCookie (key) {
  const value = Cookies.get(key)
  if (value) {
    return JSON.parse(value)
  }
  return ''
}

function removeCookie (key) {
  Cookies.remove(key)
}
const storeUtils = {
  setCookie,
  removeCookie,
  getCookie
}

export default storeUtils
