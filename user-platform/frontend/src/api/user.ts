
// 登录
export const login = async (username: string, password: string) => {
  const response = await fetch('/users/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({ username, password })
  })
  if (!response.ok) {
    throw new Error('Network response was not ok')
  }
  return response.json()
}

// 注册
export const register = async (username: string, password: string) => {
  const response = await fetch('/users/register', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({ username, password })
  })
  if (!response.ok) {
    throw new Error('Network response was not ok')
  }
  return response.json()
}
