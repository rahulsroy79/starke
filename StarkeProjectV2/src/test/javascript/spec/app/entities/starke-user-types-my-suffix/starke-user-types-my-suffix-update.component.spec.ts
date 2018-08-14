/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Starkev2TestModule } from '../../../test.module';
import { StarkeUserTypesMySuffixUpdateComponent } from 'app/entities/starke-user-types-my-suffix/starke-user-types-my-suffix-update.component';
import { StarkeUserTypesMySuffixService } from 'app/entities/starke-user-types-my-suffix/starke-user-types-my-suffix.service';
import { StarkeUserTypesMySuffix } from 'app/shared/model/starke-user-types-my-suffix.model';

describe('Component Tests', () => {
    describe('StarkeUserTypesMySuffix Management Update Component', () => {
        let comp: StarkeUserTypesMySuffixUpdateComponent;
        let fixture: ComponentFixture<StarkeUserTypesMySuffixUpdateComponent>;
        let service: StarkeUserTypesMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [StarkeUserTypesMySuffixUpdateComponent]
            })
                .overrideTemplate(StarkeUserTypesMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StarkeUserTypesMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StarkeUserTypesMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new StarkeUserTypesMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.starkeUserTypes = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new StarkeUserTypesMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.starkeUserTypes = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
