VK.init({apiId: 51433610})

VK.Widgets.Auth("vk_auth", {
    width: "500px",
    onAuth: function (data) {
        console.log(data)
    }
})