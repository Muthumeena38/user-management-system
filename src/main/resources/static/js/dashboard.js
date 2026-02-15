document.addEventListener('DOMContentLoaded', () => {
    fetch('/api/users/profile')
        .then(response => {
            if (!response.ok) throw new Error('Not authenticated');
            return response.json();
        })
        .then(data => {
            document.getElementById('user-name').textContent = data.name;
            document.getElementById('profile-name').textContent = data.name;
            document.getElementById('profile-email').textContent = data.email;
            if (data.picture) {
                document.getElementById('user-avatar').src = data.picture;
            }
        })
        .catch(err => {
            console.error('Error fetching profile:', err);
            window.location.href = '/index.html';
        });
});
