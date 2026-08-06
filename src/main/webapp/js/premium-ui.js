/* FoodHub presentation enhancements. No forms, routes, data, sessions, or application state are changed. */
(function () {
  'use strict';
  const reducedMotion = window.matchMedia('(prefers-reduced-motion: reduce)').matches;

  function makeStarfield() {
    if (reducedMotion || !window.THREE || document.getElementById('uf-starfield')) return;
    const THREE = window.THREE;
    const canvas = document.createElement('canvas');
    canvas.id = 'uf-starfield'; canvas.setAttribute('aria-hidden', 'true');
    document.body.prepend(canvas);

    const renderer = new THREE.WebGLRenderer({ canvas: canvas, alpha: true, antialias: false });
    renderer.setPixelRatio(Math.min(window.devicePixelRatio || 1, 1.5));
    const scene = new THREE.Scene();
    scene.fog = new THREE.FogExp2(0x05070b, .00072);
    const camera = new THREE.PerspectiveCamera(58, 1, 1, 2200); camera.position.z = 850;
    const count = window.innerWidth < 700 ? 850 : 1900;
    const positions = new Float32Array(count * 3);
    const colors = new Float32Array(count * 3);
    const speeds = new Float32Array(count);
    const drift = new Float32Array(count);
    for (let i = 0; i < count; i += 1) {
      const p = i * 3;
      positions[p] = (Math.random() - .5) * 2100;
      positions[p + 1] = (Math.random() - .5) * 1250;
      positions[p + 2] = Math.random() * 1900 - 950;
      const cyan = Math.random() > .66;
      colors[p] = cyan ? .18 : .82; colors[p + 1] = cyan ? .83 : .9; colors[p + 2] = cyan ? .94 : 1;
      speeds[i] = .18 + Math.random() * .82; drift[i] = (Math.random() - .5) * .065;
    }
    const geometry = new THREE.BufferGeometry();
    geometry.setAttribute('position', new THREE.BufferAttribute(positions, 3));
    geometry.setAttribute('color', new THREE.BufferAttribute(colors, 3));
    const material = new THREE.PointsMaterial({ size: 2.7, sizeAttenuation: true, transparent: true, opacity: .78, vertexColors: true, depthWrite: false, blending: THREE.AdditiveBlending });
    scene.add(new THREE.Points(geometry, material));
    const resize = function () { const w = window.innerWidth, h = window.innerHeight; camera.aspect = w / h; camera.updateProjectionMatrix(); renderer.setSize(w, h, false); };
    resize(); window.addEventListener('resize', resize, { passive: true });
    let frame = 0;
    const animate = function () {
      frame += 1; const attribute = geometry.attributes.position;
      for (let i = 0; i < count; i += 1) {
        const p = i * 3; positions[p + 2] += speeds[i]; positions[p] += drift[i];
        if (positions[p + 2] > 900) { positions[p + 2] = -950; positions[p] = (Math.random() - .5) * 2100; positions[p + 1] = (Math.random() - .5) * 1250; }
      }
      attribute.needsUpdate = true;
      material.opacity = .68 + Math.sin(frame * .015) * .1;
      renderer.render(scene, camera); requestAnimationFrame(animate);
    };
    animate();
  }

  function initAuthBikeScene() {
    const canvas = document.getElementById('auth-bike-scene');
    if (reducedMotion || !canvas || !window.THREE || window.innerWidth <= 900) return;
    const THREE = window.THREE;
    const renderer = new THREE.WebGLRenderer({ canvas: canvas, alpha: true, antialias: true });
    renderer.setPixelRatio(Math.min(window.devicePixelRatio || 1, 1.5));
    const scene = new THREE.Scene();
    const camera = new THREE.PerspectiveCamera(40, 1, .1, 100); camera.position.set(0, 0, 12);
    const cream = new THREE.MeshBasicMaterial({ color: 0xfbf8f0 });
    const green = new THREE.MeshBasicMaterial({ color: 0xdce5dc });
    const bikeGreen = new THREE.MeshBasicMaterial({ color: 0x006241 });
    const dark = new THREE.MeshBasicMaterial({ color: 0x102f29 });
    const bag = new THREE.MeshBasicMaterial({ color: 0xf2f0eb });
    const skin = new THREE.MeshBasicMaterial({ color: 0xe4b48f });
    const routeMat = new THREE.LineBasicMaterial({ color: 0xdce5dc, transparent: true, opacity: .46 });
    const frameMat = new THREE.LineBasicMaterial({ color: 0xfbf8f0, transparent: true, opacity: .82 });
    const routePoints = [];
    for (let x = -7; x <= 7; x += .15) routePoints.push(new THREE.Vector3(x, -2.55 + Math.sin(x * .75) * .28, -.2));
    const route = new THREE.Line(new THREE.BufferGeometry().setFromPoints(routePoints), routeMat);
    scene.add(route);

    const bike = new THREE.Group();

    const rearWheel = new THREE.Mesh(new THREE.TorusGeometry(.42, .09, 14, 34), dark);
    const frontWheel = new THREE.Mesh(new THREE.TorusGeometry(.42, .09, 14, 34), dark);
    rearWheel.position.set(-.95, -.1, 0);
    frontWheel.position.set(1.08, -.1, 0);
    const rearHub = new THREE.Mesh(new THREE.CircleGeometry(.13, 24), green);
    const frontHub = new THREE.Mesh(new THREE.CircleGeometry(.13, 24), green);
    rearHub.position.copy(rearWheel.position);
    frontHub.position.copy(frontWheel.position);
    bike.add(rearWheel, frontWheel, rearHub, frontHub);

    const base = new THREE.Mesh(new THREE.BoxGeometry(1.65, .34, .22), bikeGreen);
    base.position.set(.05, .35, 0);
    base.rotation.z = .04;
    bike.add(base);
    const frontCover = new THREE.Mesh(new THREE.BoxGeometry(.7, .46, .22), bikeGreen);
    frontCover.position.set(.76, .62, 0);
    frontCover.rotation.z = -.22;
    bike.add(frontCover);
    const seat = new THREE.Mesh(new THREE.BoxGeometry(.78, .16, .2), dark);
    seat.position.set(-.42, .72, 0);
    seat.rotation.z = -.08;
    bike.add(seat);
    const headLight = new THREE.Mesh(new THREE.CircleGeometry(.12, 22), cream);
    headLight.position.set(1.28, .76, .02);
    bike.add(headLight);
    const handle = new THREE.BufferGeometry().setFromPoints([
      new THREE.Vector3(.84, .82, 0),
      new THREE.Vector3(1.12, 1.08, 0),
      new THREE.Vector3(1.42, 1.08, 0)
    ]);
    bike.add(new THREE.Line(handle, frameMat));

    const rider = new THREE.Group();
    const torso = new THREE.Mesh(new THREE.BoxGeometry(.34, .72, .18), cream);
    torso.position.set(-.18, 1.23, 0);
    torso.rotation.z = -.3;
    rider.add(torso);
    const backpack = new THREE.Mesh(new THREE.BoxGeometry(.38, .5, .2), bag);
    backpack.position.set(-.55, 1.2, -.02);
    backpack.rotation.z = -.2;
    rider.add(backpack);
    const strap = new THREE.Mesh(new THREE.BoxGeometry(.08, .56, .22), dark);
    strap.position.set(-.36, 1.2, .02);
    strap.rotation.z = -.24;
    rider.add(strap);
    const head = new THREE.Mesh(new THREE.SphereGeometry(.2, 24, 24), skin);
    head.position.set(.02, 1.75, 0);
    rider.add(head);
    const helmet = new THREE.Mesh(new THREE.SphereGeometry(.23, 24, 24, 0, Math.PI * 2, 0, Math.PI * .58), bikeGreen);
    helmet.position.set(.02, 1.78, .01);
    helmet.rotation.z = -.12;
    rider.add(helmet);
    const visor = new THREE.Mesh(new THREE.BoxGeometry(.22, .05, .03), dark);
    visor.position.set(.15, 1.75, .03);
    visor.rotation.z = -.1;
    rider.add(visor);
    const armGeometry = new THREE.CylinderGeometry(.035, .04, .72, 10);
    const leftArm = new THREE.Mesh(armGeometry, cream);
    leftArm.position.set(.22, 1.27, 0);
    leftArm.rotation.z = -1.08;
    rider.add(leftArm);
    const rightArm = new THREE.Mesh(armGeometry, cream);
    rightArm.position.set(.38, 1.19, 0);
    rightArm.rotation.z = -1.18;
    rider.add(rightArm);
    const legGeometry = new THREE.CylinderGeometry(.045, .055, .78, 10);
    const rearLeg = new THREE.Mesh(legGeometry, dark);
    rearLeg.position.set(-.33, .77, 0);
    rearLeg.rotation.z = .62;
    rider.add(rearLeg);
    const frontLeg = new THREE.Mesh(legGeometry, dark);
    frontLeg.position.set(.1, .78, 0);
    frontLeg.rotation.z = -.72;
    rider.add(frontLeg);
    bike.add(rider);

    const exhaust = new THREE.Mesh(new THREE.BoxGeometry(.5, .1, .14), dark);
    exhaust.position.set(-1.25, .24, 0);
    exhaust.rotation.z = .18;
    bike.add(exhaust);
    scene.add(bike);

    for (let index = 0; index < 4; index += 1) {
      const x = -5.7 + index * 3.8;
      const marker = new THREE.Mesh(new THREE.CircleGeometry(.08, 18), green);
      marker.position.set(x, -2.12 + Math.sin(x * .75) * .28, 0);
      scene.add(marker);
    }

    const resize = function () { const width = canvas.clientWidth; const height = canvas.clientHeight; if (!width || !height) return; camera.aspect = width / height; camera.updateProjectionMatrix(); renderer.setSize(width, height, false); };
    resize(); window.addEventListener('resize', resize, { passive: true });
    const animate = function (time) {
      const progress = (time * .000055) % 1;
      const x = -6.8 + progress * 13.6;
      bike.position.x = x;
      bike.position.y = -2.08 + Math.sin(x * .75) * .28;
      bike.rotation.z = Math.cos(x * .75) * .08;
      rider.position.y = Math.sin(time * .006) * .035;
      rearWheel.rotation.z -= .16;
      frontWheel.rotation.z -= .16;
      renderer.render(scene, camera);
      requestAnimationFrame(animate);
    };
    requestAnimationFrame(animate);
  }

  function splitWords(element) {
    if (!element || element.dataset.split || element.children.length) return [element];
    const words = element.textContent.trim().split(/\s+/); if (words.length < 2) return [element];
    element.dataset.split = 'true';
    element.innerHTML = words.map(function (word) { return '<span class="split-word">' + word + '&nbsp;</span>'; }).join('');
    return Array.from(element.querySelectorAll('.split-word'));
  }

  function animateInterface() {
    const cards = Array.from(document.querySelectorAll('.restaurant-card, .menu-item-card:not(.menu-list-item), .feature-item, .stat-card, .activity-card, .order-card, .delivery-card, .manage-card, .home-restaurant-card, .dish-card, .process-timeline article'));
    if (!reducedMotion && window.gsap) {
      const gsap = window.gsap;
      if (window.ScrollTrigger) gsap.registerPlugin(window.ScrollTrigger);
      gsap.from('.page-header, .landing-header, .nav-bar', { y: -20, opacity: 0, duration: .75, ease: 'power3.out' });
      const targets = Array.from(document.querySelectorAll('#hero-title, .page-title-section h1, .restaurant-banner h1'));
      const words = targets.reduce(function (allWords, target) { return allWords.concat(splitWords(target)); }, []);
      gsap.from(words, { yPercent: 110, opacity: 0, rotateX: -42, duration: .9, stagger: .045, ease: 'power4.out', delay: .12 });
      gsap.from('.hero-subtitle, .page-subtitle, .hero-badge, .page-badge, .cta-actions', { y: 18, opacity: 0, duration: .6, stagger: .08, ease: 'power3.out', delay: .28 });
      cards.forEach(function (card, index) {
        const animation = { y: 28, opacity: 0, duration: .66, delay: (index % 5) * .045, ease: 'power3.out', overwrite: 'auto' };
        if (window.ScrollTrigger) animation.scrollTrigger = { trigger: card, start: 'top 91%', once: true };
        gsap.from(card, animation);
      });
      document.querySelectorAll('.home-stat strong').forEach(function (stat) {
        const numberNode = Array.from(stat.childNodes).find(function (node) { return node.nodeType === Node.TEXT_NODE && /\d/.test(node.nodeValue); });
        if (!numberNode) return;
        const target = Number(numberNode.nodeValue.replace(/[^\d.]/g, '')); if (!Number.isFinite(target)) return;
        const counter = { value: 0 };
        gsap.to(counter, { value: target, duration: 1.25, ease: 'power2.out', snap: { value: 1 }, scrollTrigger: window.ScrollTrigger ? { trigger: stat, start: 'top 88%', once: true } : undefined, onUpdate: function () { numberNode.nodeValue = Math.round(counter.value); } });
      });
    } else {
      cards.forEach(function (card, index) { card.classList.add('reveal'); card.style.transitionDelay = Math.min(index % 6, 5) * 55 + 'ms'; });
      if (!reducedMotion && 'IntersectionObserver' in window) {
        const observer = new IntersectionObserver(function (entries) {
          entries.forEach(function (entry) {
            if (entry.isIntersecting) {
              entry.target.classList.add('is-visible');
              observer.unobserve(entry.target);
            }
          });
        }, { threshold: .12 });
        document.querySelectorAll('.reveal').forEach(function (element) { observer.observe(element); });
      } else document.querySelectorAll('.reveal').forEach(function (element) { element.classList.add('is-visible'); });
    }
  }

  function initMenuCartPreview() {
    var bar = document.querySelector('.menu-cart-bar');
    var countNode = document.getElementById('menu-cart-count');
    var labelNode = document.getElementById('menu-cart-label');
    var viewCartButton = document.getElementById('menu-view-cart');
    var forms = Array.from(document.querySelectorAll('.menu-add-form'));
    if (!bar || !countNode || !viewCartButton || !forms.length) return;

    var activeForm = null;
    var selectedForms = function () {
      return forms.filter(function (form) { return form.classList.contains('is-added'); });
    };
    var updateBar = function () {
      var count = selectedForms().reduce(function (total, form) {
        var input = form.querySelector('.cart-quantity-input');
        return total + Math.max(0, Number(input ? input.value : 0) || 0);
      }, 0);
      countNode.textContent = String(count);
      if (labelNode) labelNode.textContent = count === 1 ? 'item added' : 'items added';
      bar.classList.toggle('is-visible', count > 0);
    };

    updateBar();

    forms.forEach(function (form) {
      var addButton = form.querySelector('[data-cart-add]');
      var minusButton = form.querySelector('[data-cart-minus]');
      var plusButton = form.querySelector('[data-cart-plus]');
      var quantityInput = form.querySelector('.cart-quantity-input');
      var actionInput = form.querySelector('.cart-action-input');
      var quantityCount = form.querySelector('.menu-qty-count');
      if (!addButton || !minusButton || !plusButton || !quantityInput || !actionInput || !quantityCount) return;

      var setQuantity = function (value, action) {
        var next = Math.max(0, value);
        quantityInput.value = String(next);
        actionInput.value = action || 'update';
        quantityCount.textContent = String(Math.max(1, next));
        form.classList.toggle('is-added', next > 0);
        if (next > 0) activeForm = form;
        form.submit();
        updateBar();
      };

      addButton.addEventListener('click', function () { setQuantity(1, 'add'); });
      plusButton.addEventListener('click', function () { setQuantity((Number(quantityInput.value) || 0) + 1, 'update'); });
      minusButton.addEventListener('click', function () { setQuantity((Number(quantityInput.value) || 0) - 1, 'update'); });
    });

    viewCartButton.addEventListener('click', function () {
      window.location.href = 'cart.jsp';
    });
  }

  document.addEventListener('DOMContentLoaded', function () {
    makeStarfield(); initMenuCartPreview(); animateInterface();
    var header = document.querySelector('.page-header, .landing-header, .nav-bar');
    if (header) { var updateHeader = function () { header.classList.toggle('is-scrolled', window.scrollY > 10); }; updateHeader(); window.addEventListener('scroll', updateHeader, { passive: true }); }

    document.querySelectorAll('.toggle-password').forEach(function (button) {
      button.addEventListener('click', function () {
        var input = button.parentElement.querySelector('input[type="password"], input[type="text"]'); if (!input) return;
        var hidden = input.type === 'password'; input.type = hidden ? 'text' : 'password'; button.setAttribute('aria-pressed', String(hidden));
        var icon = button.querySelector('i'); if (icon) icon.className = hidden ? 'fa-solid fa-eye-slash' : 'fa-solid fa-eye';
      });
    });
    document.querySelectorAll('.filter-pill, .category-pill').forEach(function (pill) {
      pill.addEventListener('click', function () { var group = pill.parentElement; group.querySelectorAll('.filter-pill, .category-pill').forEach(function (item) { item.classList.remove('filter-active', 'category-active'); }); pill.classList.add(pill.classList.contains('category-pill') ? 'category-active' : 'filter-active'); });
    });
    var search = document.querySelector('.search-input'); var restaurantCards = Array.from(document.querySelectorAll('.restaurant-card'));
    if (search && restaurantCards.length) search.addEventListener('input', function () { var query = search.value.trim().toLowerCase(); restaurantCards.forEach(function (card) { var wrapper = card.closest('a') || card; wrapper.style.display = !query || card.textContent.toLowerCase().includes(query) ? '' : 'none'; }); });
    document.querySelectorAll('.restaurant-card, .menu-item-card:not(.menu-list-item)').forEach(function (card) {
      card.addEventListener('pointermove', function (event) {
        if (reducedMotion) return;
        var rect = card.getBoundingClientRect();
        var rotateY = ((event.clientX - rect.left) / rect.width - .5) * 4;
        var rotateX = ((event.clientY - rect.top) / rect.height - .5) * -4;
        card.style.transform = 'perspective(900px) translateY(-8px) rotateX(' + rotateX + 'deg) rotateY(' + rotateY + 'deg)';
      });
      card.addEventListener('pointerleave', function () { card.style.transform = ''; });
    });
    if (!reducedMotion && window.gsap) document.querySelectorAll('button, .btn-primary, .btn-cta, .add-to-cart-btn, .place-order-btn, .card-order-btn').forEach(function (element) {
      element.addEventListener('pointermove', function (event) { var rect = element.getBoundingClientRect(); window.gsap.to(element, { x: (event.clientX - rect.left - rect.width / 2) * .12, y: (event.clientY - rect.top - rect.height / 2) * .12, duration: .28, ease: 'power2.out', overwrite: true }); });
      element.addEventListener('pointerleave', function () { window.gsap.to(element, { x: 0, y: 0, duration: .55, ease: 'elastic.out(1,.45)', overwrite: true }); });
    });
  });
}());
