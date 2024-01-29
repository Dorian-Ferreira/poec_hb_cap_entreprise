function initFilter(){
    const filter = document.querySelector("input[data-filter]");

    if(filter){
        const submit = filter.nextElementSibling;
        filter.addEventListener('keydown',(e)=>{
            if(e.key==="Enter"){
                filterWith(filter.value);
            }
        })
        if(submit){
            submit.addEventListener('click',()=>{
                filterWith(filter.value);
            })
        }
    }
}

function filterWith(value){
    var url = new URL(location.href);
    if(value.trim()){
        url.searchParams.set("search",value);
    }
    url.searchParams.set("page","1");
    url.search = url.search.replaceAll("%2C",",");
    location.href = url.href;
}

window.addEventListener('load',()=>{
    initFilter();
    moderationFilter();
})

function moderationFilter(){
    const selector = document.querySelector("select[moderationFilter]");
    var url = new URL(location.href);
    if(selector){
        selector.addEventListener('change',()=>{
            const val = selector.value;
            if(val){
                if(parseInt(val)===0){
                    url.searchParams.delete("moderation");
                }
                if(parseInt(val)>=1){
                    url.searchParams.set("moderation",val);
                }
            }
            url.search = url.search.replaceAll("%2C",",");
            location.href=url.href;
        })
        const val = url.searchParams.get("moderation");
        if(val && parseInt(val)>=0){
            selector.value=val;
        }
    }
}