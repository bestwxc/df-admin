
function notEmpty (str) {
  return str && str.length > 0
}
function capFirst (str) {
  return notEmpty(str) ? str.substr(0, 1).toUpperCase() + str.substr(1) : ''
}
function uncapFirst (str) {
  return notEmpty(str) ? str.substr(0, 1).toLowerCase() + str.substr(1) : ''
}
const stringUtils = {
  notEmpty,
  capFirst,
  uncapFirst
}
export default stringUtils
