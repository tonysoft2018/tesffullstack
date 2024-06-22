const apiurl = 'http://localhost:8080/api/v1';

export const ApiEndPoint = {
  Person: {
    getAll: `${apiurl}/persons`,
    get: `${apiurl}/persons`,
    add: `${apiurl}/persons`,
    update: `${apiurl}/persons`,
    delete: `${apiurl}/persons`
  }
};